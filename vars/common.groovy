def compile() {
    if(app_lang == "nodejs") {
        sh 'npm install'
    }
    if(app_lang == "maven") {
        sh 'mvn package'
    }
}

def testcases() {
    //npm test
    //mvn test
    //python -m unitests
    //go test
    sh 'echo OK'
}

def codequality() {

    withAWSParameterStore(credentialsId: 'PARAM1', naming: 'absolute', path: '/sonarqube', recursive: false, regionName: 'us-east-1'){
       // sh 'sonar-scanner -Dsonar.host.url=http://172.31.91.230:9000 -Dsonar.login=${SONARQUBE_USER} -Dsonar.password=${SONARQUBE_PASS} -Dsonar.projectKey=${component} ${sonar_extra_opts} -Dsonar.qualitygate.wait=true'
        sh 'echo OK'
    }


}