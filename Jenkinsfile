pipeline 
{
    agent any
    
    tools{
        maven 'maven'
        }

    stages 
    {
        stage("Build") {
         steps {
            echo("Building is done")
         }
      }
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
        
        
        
                
        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/mohangoudGit/GitRepoForJenkinsPipeline.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resource/HomePageTest.xml -Denv=qa"
                    
                }
            }
        }
            
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
     
    
        
        stage('Publish ChainTest Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'target', 
                                  reportFiles: 'Index.html', 
                                  reportName: 'HTML Regression ChainTestReport.html', 
                                  reportTitles: ''])
            }
        }
      	
     
     stage('Publish TestNG Report') 
     
     { steps
     
      { testNG( reportFilenamePattern: '**/testng-results.xml' ) 
      
      }
      
       }
            
        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }
        
        
        
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }

 }       
         post{
  
             always{
              
                 
                 mail to: 'jenkins.frameworkdemo@gmail.com',
                subject: "Status of Job: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: "The build completed with status: ${currentBuild.currentResult}.\nView log details here: ${env.BUILD_URL}"
            
               emailext (
              
           to: 'jenkins.frameworkdemo@gmail.com',
            subject: "Build ${currentBuild.currentResult}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """Status: ${currentBuild.currentResult}Check console output here: ${env.BUILD_URL}""",
            
        
            )
          
             }

  
         }
   
        
    }
    
    
    


