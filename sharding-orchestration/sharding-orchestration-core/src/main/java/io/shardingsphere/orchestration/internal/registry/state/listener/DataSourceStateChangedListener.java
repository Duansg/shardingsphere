/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
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
 * </p>
 */

package io.shardingsphere.orchestration.internal.registry.state.listener;

import com.google.common.base.Strings;
import io.shardingsphere.orchestration.internal.registry.listener.PostShardingOrchestrationEventListener;
import io.shardingsphere.orchestration.internal.registry.state.event.DisabledStateChangedEvent;
import io.shardingsphere.orchestration.internal.registry.state.node.StateNode;
import io.shardingsphere.orchestration.internal.registry.state.schema.OrchestrationShardingSchema;
import io.shardingsphere.orchestration.internal.registry.state.service.DataSourceService;
import io.shardingsphere.orchestration.reg.api.RegistryCenter;
import io.shardingsphere.orchestration.reg.listener.DataChangedEvent;
import io.shardingsphere.orchestration.reg.listener.DataChangedEvent.ChangedType;

/**
 * Data source state changed listener.
 *
 * @author caohao
 * @author panjuan
 */
public final class DataSourceStateChangedListener extends PostShardingOrchestrationEventListener {
    
    private final DataSourceService dataSourceService;
    
    public DataSourceStateChangedListener(final String name, final RegistryCenter regCenter) {
        super(regCenter, new StateNode(name).getDataSourcesNodeFullRootPath());
        dataSourceService = new DataSourceService(name, regCenter);
    }
    
    @Override
    protected DisabledStateChangedEvent createShardingOrchestrationEvent(final DataChangedEvent event) {
        return getDisabledStateChangedEvent(event, dataSourceService.getDisabledSlaveShardingSchema(event.getKey()));
    }
    
    private DisabledStateChangedEvent getDisabledStateChangedEvent(final DataChangedEvent event, final OrchestrationShardingSchema shardingSchema) {
        return !Strings.isNullOrEmpty(event.getValue()) && ChangedType.UPDATED == event.getChangedType()
                ? new DisabledStateChangedEvent(shardingSchema, true) : new DisabledStateChangedEvent(shardingSchema, false);
    }
}
