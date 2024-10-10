#!/bin/bash

bold=$(tput bold)
normal=$(tput sgr0)

# Warmup
echo "${bold}Warming up:"
for i in {0..3}
do
  echo $normal$((i+1))
  go-wrk -c 500 -d 2 -T 50000 -M POST http://localhost:808$i > /dev/null
done

echo "${bold}Warmed up. Running real benchmark:"

sleep 5

for i in {0..3}
do
  echo $normal$((i+1))
  go-wrk -c 5000 -d 30 -T 50000 -M POST http://localhost:808$i
  echo "------------------------------------"
  sleep 1
done

