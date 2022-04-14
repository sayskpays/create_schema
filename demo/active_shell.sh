#!/bin/bash

ChekUser=$(whoami)

if [ ${ChekUser} != "root" ]; then
echo "Successfully Hawk, Administrator Active"
./home/hong/tibco/administrator/domain/eai_test/bin/tibcoadmin_eai_test &
sleep 10
./home/hong/tibco/tra/domain/eai_test/hawkagent_eai_test &
exit
fi
echo "Please Change User"
