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

import com.company.sample.gui.user.UserExtBrowser;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.export.FileDataProvider;
import com.haulmont.cuba.gui.export.ResourceDataProvider;
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
            Embedded embedded = componentsFactory.createComponent(Embedded.class);

            embedded.setType(Embedded.Type.IMAGE);
            embedded.setWidth("25px");
            embedded.setHeight("25px");

            FileDescriptor userImageFile = task.getAssignee().getImage();
            if (userImageFile == null) {
                ResourceDataProvider dataProvider = new ResourceDataProvider(UserExtBrowser.DEFAULT_USER_IMAGE_PATH);
                embedded.setSource(UserExtBrowser.DEFAULT_USER_IMAGE_NAME, dataProvider);
            } else {
                FileDataProvider dataProvider = new FileDataProvider(userImageFile);
                embedded.setSource(userImageFile.getId() + "." + userImageFile.getExtension(), dataProvider);
            }

            Label userLogin = componentsFactory.createComponent(Label.class);
            userLogin.setValue(task.getAssignee().getLogin());
            userLogin.setAlignment(Alignment.MIDDLE_LEFT);

            HBoxLayout hBox = componentsFactory.createComponent(HBoxLayout.class);
            hBox.setSpacing(true);

            hBox.add(embedded);
            hBox.add(userLogin);

            return hBox;
        });
    }
}