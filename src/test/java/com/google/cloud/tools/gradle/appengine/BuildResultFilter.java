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

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.BuildTask;

import java.util.List;

import javax.annotation.Nullable;

/**
 * Tools to filter gradle test kit runner results
 */
public class BuildResultFilter {

  public static List<String> extractTasks(BuildResult buildResult) {

    return FluentIterable
        .from(buildResult.getTasks())
        .transform(new Function<BuildTask, String>() {
          @Nullable
          @Override
          public String apply(@Nullable BuildTask buildTask) {
            return buildTask.getPath();
          }
        })
        .toList();
  }

}
