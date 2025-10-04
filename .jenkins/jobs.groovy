// This defines a new pipeline job named 'monorepo-pipeline'
pipelineJob('monorepo-pipeline') {
    // A brief description for the job
    description('CI/CD pipeline for the monorepo application.')

    // This section configures the SCM, just like in the UI
    definition {
        cpsScm {
            scm {
                git {
                    // Replace with your GitHub username
                    remote {
                        url('https://github.com/0903lokchan/cicd-lab-monorepo.git')
                    }
                    branch('*/main')
                }
            }
            // Tells Jenkins the name of the pipeline script file
            scriptPath('Jenkinsfile')
        }
    }
}
