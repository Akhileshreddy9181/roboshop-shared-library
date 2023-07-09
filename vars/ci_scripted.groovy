def call () {
    if(env.sonar_extra_opts == "") {
        env.sonar_extra_opts=""
    }
    node('workstation') {

        stage('Check Out Code') {
            cleanWs()
            git branch: 'main', url: 'https://github.com/akhileshreddy9181/cart'
        }

        sh 'env'

        if(env.BRANCH_NAME != "main") {
            stage('Compile/Build') {
                common.compile()
            }
        }

        if(!env.TAG_NAME) {
            stage('Test Cases') {
                common.testcases()
            }
        }



        stage('Code Quality') {
            common.codequality()
        }

    }

}