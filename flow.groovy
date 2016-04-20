echo "Loading Master Branch"
echo env.BRANCH_NAME

stage 'Build'
node(){
    // COMPILE AND JUNIT
    
    ensureMaven()
    //sh 'mvn clean install'
    //stash includes: 'target/petclinic.war', name: 'war'
    //step $class: 'hudson.tasks.junit.JUnitResultArchiver', testResults: 'target/surefire-reports/*.xml'
    echo "INFO - Ending build phase"
}

checkpoint "Build Complete"

stage 'Dev Deploy'
node()
{
	echo "Deploying to Dev"
	//unstash 'war'
	//undeploy previous app 
	//sh 'rm -rf /var/environments/dev/*.war'
	//sleep 5
	//deploy app
	//sh 'cp target/petclinic.war /var/environments/dev'
	//sleep 5 //wait for initialization
	echo "Deployed to Dev"
}

checkpoint "Deployed to Dev"

stage name: 'Quality analysis and Perfs'
parallel(qualityAnalysis: {

    node(){
        // //RUN SONAR ANALYSIS
        echo "INFO - Starting SONAR"
        //ensureMaven()
        //sh 'mvn -o sonar:sonar'
        echo "INFO - Ending SONAR"
    }
}, performanceTest: {

    node(){
        // DEPLOY ON PERFS AND RUN JMETER STRESS TEST
        echo "INFO - starting Perf Tests"
        //sh 'mvn -o jmeter:jmeter'
        echo "INFO - Ending Perf Tests"
    }
}
)

stage "QA Approval"
timeout(time: 10, unit: 'SECONDS')
{
   try
   { 
      input message: 'Deploy to QA?'
   } 
   catch(Exception e)
   {
      echo "No input provided, resuming build"
   } 
}

stage 'QA Deploy'
node()
{
	echo "Deploying to QA"
	//sh 'rm -rf /var/environments/qa/*.war'
	//sleep 5
	//sh 'cp target/petclinic.war /var/environments/qa'
	//sleep 5
	//echo "Deployed to QA"
}

checkpoint "Deployed to QA"

stage 'Staging Deploy'
timeout(time: 10, unit: 'SECONDS')
{
   try
   {
    input message: "Deploy to Staging?"
   } 
   catch(Exception e)
   {
      echo "No input provided, resuming build"
   } 
}

node()
{
	echo "Deploying to Staging"
	//sh 'rm -rf /var/environments/staging/*.war'
	//sleep 5
	//sh 'cp target/petclinic.war /var/environments/staging'
	//sleep 5
	echo "Deployed to Staging"
}

checkpoint "Deployed to Staging"
stage 'Prod Deploy'
timeout(time: 10, unit: 'SECONDS')
{
   input message: "Deploy to Prod?"
}

node()
{
	echo "Deploying to Prod"
	//sh 'rm -rf /var/environments/prod/*.war'
	//sleep 5
	//sh 'cp target/petclinic.war /var/environments/prod'
	//sleep 5
	echo "Deployed to Prod"
}

/**
 * Deploy Maven on the slave if needed and add it to the path
 */
def ensureMaven() {
    env.PATH = "${tool 'mvn'}/bin:${env.PATH}"
}
