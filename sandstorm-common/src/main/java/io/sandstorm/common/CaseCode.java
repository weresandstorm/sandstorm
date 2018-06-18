package io.sandstorm.common;

public enum CaseCode {

    // 1 -> 20 留给整个应用范围内通用的异常 case
    // 21 -> ... 留给各个业务子领域

    ok("0"),
    illegal_api_input("1"),
    resource_not_exist("2"),
    violate_biz_constraint("3"),
    upload_io_error("4"),
    internal_error("15"),
    load_schema_error("16"),

    // test-script: 20-24
    script_undeletable("20"),
    script_unchangeable_currently("21"),
    script_alias_exist("22"),

    // data-set: 25-29
    dataset_undeletable("25"),
    no_chunks_at_source("26"),
    dataset_unchangeable_currently("27"),
    dataset_file_name_exist("28"),

    // load-injector: 30-34
    load_injector_undeletable("30"),

    // test-job and job-execution: 35-39
    load_injector_not_enough("35"),
    job_exec_unstoppable("36"),
    job_exec_undeletable("37");

    private final String code;

    CaseCode(String code) {
        this.code = code;
    }

    public final String get() {
        return code;
    }

}
