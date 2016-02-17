# User Avatars

This is a showcase application which demonstrates how to add pictures to system users.

* The `UserExt` entity extends the `User` entity defined in the platform. We have added the `image` attribute which is a reference to the `FileDescriptor` entity. So user avatars will be stored in `FileStorage`.
* The `Task` entity is a sample domain entity which has the link to a user.
* The `user-browse.xml` screen extends the browser screen defined in the platform. We have added the `image` column which displays the user picture by means of the `Embedded` component created in the `UserExtBrowser` controller class.
* The `user-edit.xml` extended editor screen defines the components for uploading and displaying a user picture.
* The `task-browse.xml` screen displays linked users with pictures. See the `TaskBrowse` controller class for details.

To see the example in action, do the following:

* Open the project in Studio
* Click *Run > Start application server*
* If you see the warning *Database does not exist ...*, click *Create DB in background*
* Go to `http://localhost:8080/app` and log in as `admin` / `admin`
* Open *Administration > Users*, edit or create a user and set a picture for it.
* Open *Application > Tasks*, create a task and select an assignee for it from the list of users.

Based on CUBA Platform 6.0.8
