use UserManagement
go

ALTER TABLE tblUsers ADD FOREIGN KEY(roleID) REFERENCES tblRoles(roleID)
go

ALTER TABLE tblOrders ADD FOREIGN KEY(userID) REFERENCES tblUsers(userID)
go

ALTER TABLE tblProducts ADD FOREIGN KEY(categoryID) REFERENCES tblCategories(categoryID)
go

ALTER TABLE tblOrderDetails ADD FOREIGN KEY(orderID) REFERENCES tblOrders(orderID)
go

ALTER TABLE tblOrderDetails ADD FOREIGN KEY(productID) REFERENCES tblProducts(productID)
go