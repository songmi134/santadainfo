#!/bin/sh
cd frontend
npm install -g @craco/craco
npm install
npm run build
cd ..
cp -r frontend/build/* src/main/resources/static/
