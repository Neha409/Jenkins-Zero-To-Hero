pipeline { 

    agent {
        docker {
            image 'docker:24'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
        environment {
        HOME = "/home/azureuser"
    }

    }

    stages {
        stage("GIT checkout") {
        steps {
            git branch: 'main',
                    credentialsId: 'github-token',
                    url: 'https://github.com/Neha409/Jenkins-Zero-To-Hero.git'
         }
        }

        stage("build image"){
            steps {
                script {
                    sh '''
                        echo 'Buid Docker Image'
                        docker build -t neha/cicd-e2e:${BUILD_NUMBER} python-jenkins-argocd-k8s/.

                    '''
                }
            }

        }



    }





}


  
