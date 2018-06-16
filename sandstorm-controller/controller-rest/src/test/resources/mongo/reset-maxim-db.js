db.data_chunks.remove({});
db.data_sets.remove({});
db.job_executions.remove({});
db.job_slice_executions.remove({});
db.load_injectors.update({}, { $set: { "state":"usable" } }, { multi:true });
db.test_jobs.remove({});
db.test_scripts.remove({});
