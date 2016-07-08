/*
 * Copyright (c) 2016 Google Inc. All Right Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.google.cloud.tools.gradle.appengine.task;

import com.google.cloud.tools.appengine.api.AppEngineException;
import com.google.cloud.tools.appengine.cloudsdk.CloudSdk;
import com.google.cloud.tools.appengine.cloudsdk.CloudSdkAppEngineDevServer;
import com.google.cloud.tools.appengine.cloudsdk.process.ProcessOutputLineListener;
import com.google.cloud.tools.gradle.appengine.model.hidden.CloudSdkBuilderFactory;
import com.google.cloud.tools.gradle.appengine.model.RunModel;
import com.google.cloud.tools.gradle.appengine.task.io.FileOutputLineListener;
import com.google.cloud.tools.gradle.appengine.task.io.GradleLoggerOutputListener;

import org.gradle.api.DefaultTask;
import org.gradle.api.logging.LogLevel;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Start the App Engine development server asynchronously
 */
public class DevAppServerStartTask extends DefaultTask {

  private RunModel runConfig;
  private CloudSdkBuilderFactory cloudSdkBuilderFactory;

  public void setRunConfig(RunModel runConfig) {
    this.runConfig = runConfig;
  }

  public void setCloudSdkBuilderFactory(CloudSdkBuilderFactory cloudSdkBuilderFactory) {
    this.cloudSdkBuilderFactory = cloudSdkBuilderFactory;
  }

  @TaskAction
  public void startAction() throws AppEngineException, IOException {
    ProcessOutputLineListener listener =
        new GradleLoggerOutputListener(getLogger(), LogLevel.LIFECYCLE);

    CloudSdk sdk = cloudSdkBuilderFactory.newBuilder()
        .async(true)
        .runDevAppServerWait(20)
        .addStdErrLineListener(listener)
        .addStdOutLineListener(listener)
        .build();
    CloudSdkAppEngineDevServer server = new CloudSdkAppEngineDevServer(sdk);
    server.run(runConfig);
  }

}
