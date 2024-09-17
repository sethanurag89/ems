#Employee Management System

The project is having 3 roles of employees(employee, admin, super-admin), the pages and the APIs are role basis i.e. #EMployees can only see their information and can see full list of active employees working in the organization. #Admin can add/delete only employee. #Super admin can add/delete admins, employees and other superadmin.

Admins and Superadmins can see the list of active and non active employees of organization.

If you are installing project for the first time then just uncomment the ddl.auto=create line and comment the ddl.auto=update and update the mysql information of the root folder id, password and link and then run the project, the table structure will automatically be created then run the insert dummy data query in the mysql which will create a dummy user of:- id- user@user.com password- user123

Then start the project by commenting the ddl.auto=create line and uncommenting ddl.auto=update line and run both backend and frontend and the create new employees according to use.
