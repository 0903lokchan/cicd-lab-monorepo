pipeline {
    // 1. Tell Jenkins to run this on a Kubernetes agent
    agent {
        label 'jnlp-agent'
    }

    stages {
        stage('Detect Changes') {
            steps {
                script {
                    // 2. Get a list of all files changed in the last commit
                    def changedFiles = sh(script: "git diff --name-only HEAD~1 HEAD", returnStdout: true).trim().split('\n')
                    
                    // 3. Figure out which unique service directories were affected
                    def changedServices = [:]
                    for (def file : changedFiles) {
                        if (file.startsWith("services/")) {
                            // Extracts the service name, e.g., "services/api-python/app.py" -> "api-python"
                            def serviceName = file.split('/')[1]
                            changedServices[serviceName] = true
                        }
                    }

                    // 4. If any services changed, print them out
                    if (!changedServices.isEmpty()) {
                        echo "Changes detected in the following services: ${changedServices.keySet().join(', ')}"
                        // Later, we will add build steps here
                    } else {
                        echo "No changes detected in any service."
                    }
                }
            }
        }
    }
}
