stage 'Build'
node{
    // COMPILE AND JUNIT
    def src = 'http://github.com/hdharia/spring-petclinic.git'
    git url: src
    echo "INFO - Ending build phase"
}

echo env.JOB_NAME 
echo env.BRANCH_NAME

if (env.BRANCH_NAME.startsWith("FR"))
    load DEFECT_BRANCH/flow.groovy
else if (env.BRANCH_NAME.startsWith("DR"))
    load DEFECT_BRANCH/flow.groovy
else if (env.BRANCH_NAME.startsWith("MASTER"))
	load flow.groovy
else
	throw new Exception("Unknown Branch")
    