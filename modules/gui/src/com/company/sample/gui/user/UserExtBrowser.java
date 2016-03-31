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

package com.company.sample.gui.user;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.app.security.user.browse.UserBrowser;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.Embedded.Type;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.export.FileDataProvider;
import com.haulmont.cuba.gui.export.ResourceDataProvider;
import com.company.sample.entity.UserExt;

import javax.inject.Inject;
import java.util.Map;

public class UserExtBrowser extends UserBrowser {

    public static final String DEFAULT_USER_IMAGE_PATH = "/com/company/sample/gui/user/default-avatar.jpg";
    public static final String DEFAULT_USER_IMAGE_NAME = "default-avatar.jpg";

    @Inject
    protected Table<UserExt> usersTable;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        usersTable.addGeneratedColumn("image", user -> {
            Embedded embedded = componentsFactory.createComponent(Embedded.class);
            embedded.setType(Type.IMAGE);
            embedded.setWidth("25px");
            embedded.setHeight("25px");

            FileDescriptor userImageFile = user.getImage();
            if (userImageFile == null) {
                ResourceDataProvider dataProvider = new ResourceDataProvider(DEFAULT_USER_IMAGE_PATH);
                embedded.setSource(DEFAULT_USER_IMAGE_NAME, dataProvider);
            } else {
                FileDataProvider dataProvider = new FileDataProvider(userImageFile);
                embedded.setSource(userImageFile.getId() + "." + userImageFile.getExtension(), dataProvider);
            }

            return embedded;
        });
    }
}