def call () {

    pipeline {
        agent any

        stages {
            // Build Code
            stage('Compile/Build') {
                steps {
                    script {
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