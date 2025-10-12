// This defines a new pipeline job named 'monorepo-pipeline'
pipelineJob('monorepo-pipeline') {
    // A brief description for the job
    description('CI/CD pipeline for the monorepo application.')
    
    // This section configures the SCM, just like in the UI
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/0903lokchan/cicd-lab-monorepo.git')
                    }
                    branch('*/main')
                }
            }
            scriptPath('.jenkins/Jenkinsfile')
        }
    }
}

// Separate job for Jenkins self-deployment
pipelineJob('jenkins-deploy') {
    description('Pipeline to automatically deploy changes to Jenkins itself.')

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/0903lokchan/cicd-lab-monorepo.git')
                    }
                    branch('*/main')
                }
            }
            scriptPath('.jenkins/Jenkinsfile-self-deploy')
        }
    }
}
