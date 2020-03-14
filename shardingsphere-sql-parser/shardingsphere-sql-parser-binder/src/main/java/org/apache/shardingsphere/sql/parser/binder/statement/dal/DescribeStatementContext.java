/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.sql.parser.binder.statement.dal;

import lombok.Getter;
import org.apache.shardingsphere.sql.parser.binder.segment.table.TablesContext;
import org.apache.shardingsphere.sql.parser.binder.statement.CommonSQLStatementContext;
import org.apache.shardingsphere.sql.parser.sql.segment.generic.table.SimpleTableSegment;
import org.apache.shardingsphere.sql.parser.sql.statement.dal.dialect.mysql.DescribeStatement;
import org.apache.shardingsphere.sql.parser.binder.type.TableAvailable;

import java.util.Collection;
import java.util.Collections;

/**
 * Describe statement context.
 */
@Getter
public final class DescribeStatementContext extends CommonSQLStatementContext<DescribeStatement> implements TableAvailable {
    
    private final TablesContext tablesContext;
    
    public DescribeStatementContext(final DescribeStatement sqlStatement) {
        super(sqlStatement);
        tablesContext = new TablesContext(sqlStatement.getTable());
    }
    
    @Override
    public Collection<SimpleTableSegment> getAllTables() {
        return null == getSqlStatement().getTable() ? Collections.emptyList() : Collections.singletonList(getSqlStatement().getTable());
    }
}