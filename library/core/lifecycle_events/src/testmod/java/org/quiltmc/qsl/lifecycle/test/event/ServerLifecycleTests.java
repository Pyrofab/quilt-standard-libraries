/*
 * Copyright 2021 QuiltMC
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

package org.quiltmc.qsl.lifecycle.test.event;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents;
import org.quiltmc.qsl.lifecycle.api.event.ServerWorldLoadEvents;

/**
 * Tests related to the lifecycle of a server.
 */
public final class ServerLifecycleTests implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("LifecycleEventsTest");

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.READY.register(server -> {
			LOGGER.info("Started Server!");
		});

		ServerLifecycleEvents.STOPPING.register(server -> {
			LOGGER.info("Stopping Server!");
		});

		ServerLifecycleEvents.STOPPED.register(server -> {
			LOGGER.info("Stopped Server!");
		});

		ServerWorldLoadEvents.LOAD.register((server, world) -> {
			LOGGER.info("Loaded world " + world.getRegistryKey().getValue().toString());
		});

		ServerWorldLoadEvents.UNLOAD.register((server, world) -> {
			LOGGER.info("Unloaded world " + world.getRegistryKey().getValue().toString());
		});
	}
}