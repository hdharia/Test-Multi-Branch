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
    node
    {
    	load 'DEFECT_BRANCH/flow.groovy'
    }
else if (env.BRANCH_NAME.startsWith("DR"))
    node
    {
    	load 'DEFECT_BRANCH/flow.groovy'
    }
else if (env.BRANCH_NAME.startsWith("master"))
	node
	{
		load 'flow.groovy'
	}
else
	throw new Exception("Unknown Branch")

    