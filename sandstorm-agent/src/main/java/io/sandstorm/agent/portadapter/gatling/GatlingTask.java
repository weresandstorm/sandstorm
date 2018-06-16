package io.sandstorm.agent.portadapter.gatling;

import io.sandstorm.agent.domain.JobEngineService.LoadTestCallBack;
import io.sandstorm.agent.domain.model.LocalTestJob;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.LogOutputStream;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.ShutdownHookProcessDestroyer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatlingTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(GatlingTask.class);

    private static final int GATLING_ERROR_EXIT_CODE = 143;
    private static final long DEFAULT_WATCHDOG_TIMEOUT = 24 * 3600 * 1000;
    private static final String LIB_CLASSPATH;
    private static final String SCRIPT_CLASSPATH;
    private static final String CONF_CLASSPATH;
    private final LocalTestJob theJob;
    private final String javaExecutable;
    private String workDirectory;
    private ExecuteWatchdog watchdog;
    private LoadTestCallBack resultHandler;

    private Thread worker;

    static {
        LIB_CLASSPATH = GatlingConfig.STARTUP_HOME + File.separator + "lib" + File.separator +"*";
        CONF_CLASSPATH = GatlingConfig.STARTUP_HOME + File.separator + "conf" + File.separator;
        SCRIPT_CLASSPATH = GatlingConfig.STARTUP_HOME + File.separator + "script" + File.separator +"*";
    }

    GatlingTask(LocalTestJob localTestTask, LoadTestCallBack callback) throws Exception {
        this.theJob = localTestTask;
        this.resultHandler = callback;
        this.javaExecutable = safe(findJavaExecutable());
        this.workDirectory = GatlingConfig.STARTUP_HOME;
    }

    public void startExecute() {
        worker = new Thread(this, "GatlingTask");
        worker.start();
        logger.info("GatlingTask has started!");
    }

    public void stopExecute() {
        if (watchdog != null) {
            watchdog.destroyProcess();
            logger.info("GatlingTask has stopped!");
        }
        worker.interrupt();
    }

    @Override
    public void run() {

        List<String> command = null;
        try {
            command = buildCommand();
            CommandLine cl = new CommandLine(javaExecutable);
            for (String arg : command) {
                cl.addArgument(arg, false);
            }
            logger.info("The command-line to execute gatling is : {}", cl.toString());
            Executor exec = new DefaultExecutor();
            exec.setWorkingDirectory(new File(workDirectory));
            exec.setStreamHandler(new PumpStreamHandler(new ExecutorLogOutputStream()));
            exec.setProcessDestroyer(new ShutdownHookProcessDestroyer());
            int exitValue = 0;

            watchdog = new ExecuteWatchdog(DEFAULT_WATCHDOG_TIMEOUT);
            exec.setWatchdog(watchdog);
            exec.execute(cl, resultHandler);
            exitValue = resultHandler.waitFor();

            if (exitValue != 0 && exitValue != GATLING_ERROR_EXIT_CODE) {
                throw new GatlingExecuteException("command line returned non-zero value:" + exitValue);
            }
        } catch (Exception e) {
            String errMsg = String.format("Gatling Running failed, the testTask is [%s], errorMsg is [%s]", theJob, e.getMessage());
            logger.error(errMsg, e);
        }
    }

    /**
     * The java command in shell-script like this: "$JAVA" $DEFAULT_JAVA_OPTS $JAVA_OPTS -classpath
     * "/xx/gatling-script.jar:/xx/gatling-startup.jar" io.sandstorm.gatling.GatlingStartup
     * a.b.c.simulation.SecKillActivity /xx/data /xx/report
     */
    private List<String> buildCommand() throws IOException {
        ArrayList<String> command = new ArrayList<>();
        command.addAll(gatlingJvmArgs());
        command.addAll(classPathArgs());
        command.add(GatlingConfig.STARTUP_MAIN_CLASS);
        command.addAll(gatlingArgs());
        return command;
    }

    private List<String> classPathArgs() {
        List<String> args = new ArrayList<>();
        args.add("-classpath");
        args.add(libJarToClassPath());
        return args;
    }

    private String libJarToClassPath() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(CONF_CLASSPATH).append(classPathFileSeparator());
        sb.append(LIB_CLASSPATH).append(classPathFileSeparator());
        sb.append(SCRIPT_CLASSPATH);
        return sb.toString();
    }

    private String classPathFileSeparator() {
        if (!File.separator.equals("/")) {
            return ";"; //windows os
        }
        return ":";
    }

    private List<String> gatlingJvmArgs() {
        List<String> completeGatlingJvmArgs = new ArrayList<>();
        completeGatlingJvmArgs.addAll(GatlingConfig.DEFAULT_JAVA_OPTS);
        completeGatlingJvmArgs.addAll(GatlingConfig.JAVA_JMX_OPTS);
        return completeGatlingJvmArgs;
    }

    private List<String> gatlingArgs() {
        List<String> args = new ArrayList<>();
        args.add(this.theJob.simulationToRun());
        args.add(this.theJob.superJobId());
        args.add(this.theJob.feederFileDir());
        return args;
    }

    private String findJavaExecutable() {
        String javaHome;
        javaHome = System.getProperty("java.home");
        if (javaHome == null) {
            javaHome = System.getenv("JAVA_HOME");
            if (javaHome == null) {
                throw new GatlingExecuteException("Couldn't locate java, try setting JAVA_HOME environment variable.");
            }
        }
        return javaHome + File.separator + "bin" + File.separator + "java";
    }

    private String safe(String value) {
        return value.contains(" ") ? '"' + value + '"' : value;
    }

    private static class ExecutorLogOutputStream extends LogOutputStream {
        @Override
        protected void processLine(String line, int logLevel) {
            logger.info(line);
        }
    }

}
