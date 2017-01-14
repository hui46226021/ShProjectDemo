/*
 * Copyright (C)  Tony Green, LitePal Framework Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jereibaselibrary.db.litepal.tablemanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jereibaselibrary.db.litepal.tablemanager.AssociationUpdater;
import com.jereibaselibrary.db.litepal.tablemanager.model.TableModel;
import com.jereibaselibrary.db.litepal.util.BaseUtility;
import com.jereibaselibrary.db.litepal.util.Const;
import com.jereibaselibrary.db.litepal.util.LogUtil;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * When developers defined some model classes and define them in the mapping
 * list. All corresponding tables will be created automatically. But developers
 * might realize some model classes are useless or can be optimized to remove,
 * and they somehow drop the model classes. If the tables are still in the
 * database, it will be a mess soon. So this class helps do the dropping job.
 * Keep developers' database synchronized and clean.
 * 
 * @author Tony Green
 * @since 1.0
 */
public class Dropper extends AssociationUpdater {

	/**
	 * Use the TableModel to get table name and columns name to generate SQL.
	 */
	private Collection<TableModel> mTableModels;

	/**
	 * Analyzing the table model, to see which tables has no model classes
	 * anymore and can be dropped.
	 */
	@Override
	protected void createOrUpgradeTable(SQLiteDatabase db, boolean force) {
		mTableModels = getAllTableModels();
		mDb = db;
		dropTables();
	}

	/**
	 * Drop the tables which are not exist in the mapping list to keep
	 * synchronization.
	 */
	private void dropTables() {
		List<String> tableNamesToDrop = findTablesToDrop();
		dropTables(tableNamesToDrop, mDb);
		clearCopyInTableSchema(tableNamesToDrop);
	}

	/**
	 * It will find all the tables need to drop in the database, following the
	 * rules of {@link #shouldDropThisTable(String, int)}.
	 * 
	 * @return A list contains all the table names need to drop.
	 */
	private List<String> findTablesToDrop() {
		List<String> dropTableNames = new ArrayList<String>();
		Cursor cursor = null;
		try {
			cursor = mDb.query(Const.TableSchema.TABLE_NAME, null, null, null, null, null, null);
			if (cursor.moveToFirst()) {
				do {
					String tableName = cursor.getString(cursor
							.getColumnIndexOrThrow(Const.TableSchema.COLUMN_NAME));
					int tableType = cursor.getInt(cursor
							.getColumnIndexOrThrow(Const.TableSchema.COLUMN_TYPE));
					if (shouldDropThisTable(tableName, tableType)) {
						// need to drop tableNameDB
						LogUtil.d(TAG, "need to drop " + tableName);
						dropTableNames.add(tableName);
					}
				} while (cursor.moveToNext());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return dropTableNames;
	}

	/**
	 * Get a list only with table names.
	 * 
	 * @return A list only contains table names.
	 */
	private List<String> pickTableNamesFromTableModels() {
		List<String> tableNames = new ArrayList<String>();
		for (TableModel tableModel : mTableModels) {
			tableNames.add(tableModel.getTableName());
		}
		return tableNames;
	}

	/**
	 * It gets all the table names generated by the mapping classes and create a
	 * table name list. Compare table name list with the table name passed in.
	 * If the table name is not existed in the table name list and the table
	 * type is {@link Const.TableSchema#NORMAL_TABLE}, then this table should be
	 * dropped.
	 * 
	 * @param tableName
	 *            The table name to check.
	 * @param tableType
	 *            The table type to check.
	 * @return If this table should be dropped return true. Otherwise return
	 *         false.
	 */
	private boolean shouldDropThisTable(String tableName, int tableType) {
		return !BaseUtility.containsIgnoreCases(pickTableNamesFromTableModels(), tableName)
				&& tableType == Const.TableSchema.NORMAL_TABLE;
	}
}
