def call () {
    if(env.sonar_extra_opts == "") {
        env.sonar_extra_opts=""
    }
    node('workstation') {

        stage('Check Out Code') {
            sh 'ls -l'
            cleanWs()
            sh 'ls -l'
            git branch: 'main', url: 'https://github.com/akhileshreddy9181/cart'
            sh 'ls -l'
        }
        if(env.BRANCH_NAME != "main") {
            stage('Compile/Build') {
                sh 'env'
                common.compile()
            }
        }

        stage('Test Cases') {
            common.testcases()
        }

        stage('Code Quality') {
            common.codequality()
        }

    }

}