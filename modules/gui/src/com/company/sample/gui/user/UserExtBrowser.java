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