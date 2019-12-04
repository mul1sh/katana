package org.rewedigital.katana.dsl

import org.rewedigital.katana.ComponentContext

/**
 * Provides syntax for injecting transitive dependencies via [get] within the provider body.
 *
 * @see get
 */
@ModuleDslMarker
class ProviderDsl(val context: ComponentContext)

/**
 * Provides a dependency which has already been declared in the current context (total set of modules of the
 * current component) to be able to inject transitive dependencies within a module.
 */
inline fun <reified T> ProviderDsl.get(name: Any? = null) =
    context.injectNow<T>(name = name, internal = true)
