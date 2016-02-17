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
    protected Table<Task> tasksTable;

    @Inject
    protected ComponentsFactory componentsFactory;

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