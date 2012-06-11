package er.users.migrations;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class ERUsers0 extends ERXMigrationDatabase.Migration {
	@Override
	public NSArray<ERXModelVersion> modelDependencies() {
		return null;
	}
  
	@Override
	public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		// DO NOTHING
	}

	@Override
	public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		ERXMigrationTable erActivateUserTokenTable = database.newTableNamed("ERActivateUserToken");
		erActivateUserTokenTable.newStringColumn("token", 50, false);
		erActivateUserTokenTable.newIntegerColumn("userID", false);

		erActivateUserTokenTable.addUniqueIndex("ERActivateUserToken_userID_unique_idx", erActivateUserTokenTable.existingColumnNamed("userID"));

		erActivateUserTokenTable.create();
	 	erActivateUserTokenTable.setPrimaryKey("token");

		ERXMigrationTable erUserTable = database.newTableNamed("ERUser");
		erUserTable.newStringColumn("activateUserTokenID", 50, true);
		erUserTable.newStringColumn("activationStatus", 50, false);
		erUserTable.newTimestampColumn("dateCreated", false);
		erUserTable.newStringColumn("emailAddress", 254, false);
		erUserTable.newIntegerColumn("id", false);
		erUserTable.newStringColumn("password", 60, false);
		erUserTable.newTimestampColumn("resetRequestDate", true);
		erUserTable.newStringColumn("resetToken", 50, true);
		erUserTable.newStringColumn("username", 50, false);

		erUserTable.addUniqueIndex("ERUser_username_unique_idx", erUserTable.existingColumnNamed("username"));

		erUserTable.create();
	 	erUserTable.setPrimaryKey("id");

		ERXMigrationTable erChallengeResponseTable = database.newTableNamed("ERChallengeResponse");
		erChallengeResponseTable.newStringColumn("answer", 60, false);
		erChallengeResponseTable.newIntegerColumn("challengeQuestionID", false);
		erChallengeResponseTable.newIntegerColumn("id", false);
		erChallengeResponseTable.newIntegerColumn("userID", false);

		erChallengeResponseTable.addUniqueIndex("challengeQuestionID_userID_idx", erChallengeResponseTable.existingColumnNamed("challengeQuestionID"), erChallengeResponseTable.existingColumnNamed("userID"));

		erChallengeResponseTable.create();
	 	erChallengeResponseTable.setPrimaryKey("id");

		ERXMigrationTable erCredentialTable = database.newTableNamed("ERCredential");
		erCredentialTable.newTimestampColumn("dateCreated", false);
		erCredentialTable.newIntegerColumn("id", false);
		erCredentialTable.newStringColumn("password", 60, false);
		erCredentialTable.newIntegerColumn("userID", false);


		erCredentialTable.create();
	 	erCredentialTable.setPrimaryKey("id");

		ERXMigrationTable erChallengeQuestionTable = database.newTableNamed("ERChallengeQuestion");
		erChallengeQuestionTable.newIntegerColumn("id", false);
		erChallengeQuestionTable.newStringColumn("question", 255, false);


		erChallengeQuestionTable.create();
	 	erChallengeQuestionTable.setPrimaryKey("id");

		erActivateUserTokenTable.addForeignKey("userID", "ERUser", "id");
		erUserTable.addForeignKey("activateUserTokenID", "ERActivateUserToken", "token");
		erChallengeResponseTable.addForeignKey("challengeQuestionID", "ERChallengeQuestion", "id");
		erChallengeResponseTable.addForeignKey("userID", "ERUser", "id");
		erCredentialTable.addForeignKey("userID", "ERUser", "id");
	}
}