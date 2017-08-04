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

import com.haulmont.cuba.gui.app.security.user.browse.UserBrowser;
import com.haulmont.cuba.gui.components.Image;
import com.haulmont.cuba.gui.components.Table;
import com.company.sample.entity.UserExt;

import javax.inject.Inject;
import java.util.Map;

public class UserExtBrowser extends UserBrowser {

    @Inject
    protected Table<UserExt> usersTable;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        usersTable.addGeneratedColumn("image", user -> {
            Image image = componentsFactory.createComponent(Image.class);
            image.setScaleMode(Image.ScaleMode.CONTAIN);
            image.setWidth("25px");
            image.setHeight("25px");

            image.setDatasource(usersTable.getItemDatasource(user), "image");

            return image;
        });
    }
}