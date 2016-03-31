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

import com.company.sample.entity.UserExt;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.app.security.user.edit.UserEditor;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.FileUploadField;
import com.haulmont.cuba.gui.export.FileDataProvider;
import com.haulmont.cuba.gui.export.ResourceDataProvider;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;

import javax.inject.Inject;
import java.util.Map;

public class UserExtEditor extends UserEditor {

    @Inject
    private FileUploadField userImageUpload;

    @Inject
    private FileUploadingAPI fileUploadingAPI;

    @Inject
    private Embedded userImage;

    @Override
    public void init(Map<String, Object> params) {
        userImageUpload.addFileUploadSucceedListener(event -> {
            FileDescriptor fd = userImageUpload.getFileDescriptor();
            try {
                fileUploadingAPI.putFileIntoStorage(userImageUpload.getFileId(), fd);
            } catch (FileStorageException e) {
                throw new RuntimeException("Error saving file to FileStorage", e);
            }

            FileDescriptor committedImage = dataSupplier.commit(fd);
            ((UserExt) getItem()).setImage(committedImage);

            FileDataProvider dataProvider = new FileDataProvider(committedImage);
            userImage.setSource(committedImage.getId() + "." + committedImage.getExtension(), dataProvider);

            showNotification(formatMessage(getMessage("uploadSuccessMessage"), userImageUpload.getFileName()),
                    NotificationType.HUMANIZED);
        });

        super.init(params);
    }

    @Override
    protected void postInit() {
        super.postInit();

        FileDescriptor userImageFile = ((UserExt) getItem()).getImage();

        if (userImageFile == null) {
            ResourceDataProvider dataProvider = new ResourceDataProvider(UserExtBrowser.DEFAULT_USER_IMAGE_PATH);
            userImage.setSource(UserExtBrowser.DEFAULT_USER_IMAGE_NAME, dataProvider);
        } else {
            FileDataProvider dataProvider = new FileDataProvider(userImageFile);
            userImage.setSource(userImageFile.getId() + "." + userImageFile.getExtension(), dataProvider);
        }
    }
}