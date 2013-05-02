#!/bin/sh
MIGRATION_BATCH_ID="sh/InputForTetradRrd"
BATCH_PID=`ps -ef|grep $MIGRATION_BATCH_ID |grep -v grep|awk '{print $2}'`

if [ "$BATCH_PID" != "" ]
then
        echo "$MIGRATION_BATCH_ID - PID $BATCH_PID is already running."
else
        nohup ./InputForTetradRrd.sh > /dev/null &
        echo "$MIGRATION_BATCH_ID is started"
fi
