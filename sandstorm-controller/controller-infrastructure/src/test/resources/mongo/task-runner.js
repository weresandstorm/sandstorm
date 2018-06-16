for (var i = 0; i < 100; i++) {
    var taskRunner = {};
    taskRunner.intranetIp = "192.168.100." + i;
    taskRunner.publicIp = "110.80.248." + i;
    taskRunner.hostname = "sandstorm.task-runner-" + i;
    taskRunner.state = "usable";
    db.task_runners.insert(taskRunner);
}