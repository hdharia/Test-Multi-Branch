echo "Loading Defect Branch"

stage "DEV Build"
node()
{
 echo "Performing a dev build"
}

stage "DEV Deploy"
node()
{
echo "Performing a dev deploy"
}

stage "DEV Test"
node()
{
echo "Performing a dev test"
}

stage "QA Deploy"
node()
{
echo "Performing a QA deploy"
}

stage "QA Test"
node()
{
echo "Performing a QA test"
}


