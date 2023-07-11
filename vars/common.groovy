def compile() {
    if(app_lang == "nodejs") {
        sh 'npm install'
    }
    if(app_lang == "maven") {
        sh 'mvn package ; mv target/${component}-1.0.jar ${component}.jar'
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

def prepareArtifacts() {
    sh 'echo ${TAG_NAME} >VERSION'
  if (app_lang == "nodejs" || app_lang == "angular")
    {
        sh 'zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile'
    }
    if(app_lang == "maven")
    {
        sh 'zip -r ${component}-${TAG_NAME}.zip ${component}.jar VERSION'
    }

}

def artifactUpload() {
    sh 'echo ${TAG_NAME} >VERSION'
    /*if (app_lang == "nodejs" || app_lang == "angular")
    {*/
        sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://172.31.94.39:8081/repository/${component}/${component}-${TAG_NAME}.zip'
    //}

}