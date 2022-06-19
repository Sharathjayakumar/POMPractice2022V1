pipeline {

   agent any
   stages{
       
       stage("Build"){
           steps{
               echo("Build Project")
           }
       }
       
        stage("Run Unit Tests"){
           steps{
               echo("Run unit test cases")
           }
       }
       
        stage("Run SITs"){
           steps{
               echo("run interegration test cases")
           }
       }
       
        stage("Deploy to Dev"){
           steps{
               echo("Deploy to Dev")
           }
       }
       
        stage("Deploy to QA"){
           steps{
               echo("Deploy to QA")
           }
       }
       
        stage("Run test on QA"){
           steps{
               echo("Run sanity test automation on QA")
           }
       }
       
         stage("Deploy to Stage"){
           steps{
               echo("Deploy to Stage")
           }
       }
       
        stage("Run test on Stage"){
           steps{
               echo("Run sanity test automation on Stage")
           }
       }
       
         stage("Deploy to PROD"){
           steps{
               echo("Deploy to PROD")
           }
       }
       
        stage("Run test on PROD"){
           steps{
               echo("Run sanity test automation on PROD")
           }
       }
   }
    
}
