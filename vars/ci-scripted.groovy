def call () {
    if(env.sonar_extra_opts == "") {
        env.sonar_extra_opts=""
    }
    node('workstation') {

        stage('Compile/Build') {
            sh 'env'
            common.compile()
        }

        stage('Test Cases') {
            common.testcases()
        }

        stage('Code Quality') {
            common.codequality()
        }

    }

}