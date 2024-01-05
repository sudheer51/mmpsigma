pipeline {
    agent any

    stages {
        stage('Stage1##Printing Hello') {
            steps {
                echo 'Hello World'
            }
        }
         stage('Stage2##Listing files') {
            steps {
                bat '''dir
                       cd..
                       ping www.google.com'''
            }
        }
         stage('Stage3##Execute Selenium Tests') {
            steps 
            {
                 git 'https://github.com/sudheer51/mmpsigma.git'
                 dir('mmpsigma') 
                 {
                       bat 'mvn clean test'      
                 }
            }
        }
    }
}
