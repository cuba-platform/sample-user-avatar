/*
 * Copyright (c) 2016 Haulmont
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.company.sample.gui.task;

import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.company.sample.entity.Task;

import javax.inject.Inject;
import java.util.Map;

public class TaskBrowse extends AbstractLookup {

    @Inject
    private Table<Task> tasksTable;

    @Inject
    private ComponentsFactory componentsFactory;

    @Override
    public void init(Map<String, Object> params) {

        tasksTable.addGeneratedColumn("assignee", task -> {
            Image image = componentsFactory.createComponent(Image.class);
            image.setScaleMode(Image.ScaleMode.CONTAIN);
            image.setWidth("25px");
            image.setHeight("25px");

            image.setDatasource(tasksTable.getItemDatasource(task), "assignee.image");

            Label userLogin = componentsFactory.createComponent(Label.class);
            userLogin.setValue(task.getAssignee().getLogin());
            userLogin.setAlignment(Alignment.MIDDLE_LEFT);

            HBoxLayout hBox = componentsFactory.createComponent(HBoxLayout.class);
            hBox.setSpacing(true);

            hBox.add(image);
            hBox.add(userLogin);

            return hBox;
        });
    }
}