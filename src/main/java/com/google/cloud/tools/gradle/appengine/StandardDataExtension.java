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

package com.google.cloud.tools.gradle.appengine;

import org.gradle.api.JavaVersion;

import java.io.File;

/**
 * Extension to share data from project space to model space for the {@link AppEngineStandardPlugin}
 */
public class StandardDataExtension {

  private JavaVersion javaVersion;
  private File appengineWebXml;

  public JavaVersion getJavaVersion() {
    return javaVersion;
  }

  public void setJavaVersion(JavaVersion javaVersion) {
    this.javaVersion = javaVersion;
  }

  public File getAppengineWebXml() {
    return appengineWebXml;
  }

  public void setAppengineWebXml(File appengineWebXml) {
    this.appengineWebXml = appengineWebXml;
  }
}
