def call () {
    if(env.sonar_extra_opts == "") {
        env.sonar_extra_opts=""
    }
    pipeline {
        agent any

        stages {
            // Build Code
            stage('Compile/Build') {
                steps {
                    script {
                        withAWSParameterStore(credentialsId: 'PARAM', naming: 'absolute', path: '/sonarqube/user', recursive: false, regionName: 'us-east-1') {
                            sh 'env'
                            sh 'exit 1'
                        }
                        common.compile()
                    }
                }
            }
            //Test the code
            stage('Test Cases') {
                steps {
                    script {
                        common.testcases()
                    }
                }
            }
            // Quality of Code
            stage('Code Quality') {
                steps {
                    script {
                        common.codequality()
                    }
                }
            }


        }

    }

}