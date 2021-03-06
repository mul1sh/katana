# AndroidX ViewModel extensions for Katana

The extensions declared in this artifact simplify [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
injection with Katana. Also see [katana-androidx-viewmodel-savedstate](../androidx-viewmodel-savedstate).

Create a ViewModel binding inside a module:

```kotlin
data class MyViewModel(val someDependency: SomeDependency) : ViewModel()

Module {
    
    viewModel { MyViewModel(get()) }
}
```

Inject ViewModel in your `Activity` or `Fragment`:

```kotlin
class MyFragment : Fragment(),
                   KatanaTrait {
                   
    override val component = Component(...)
    
    private val viewModel by viewModel<MyViewModel, MyFragment>() 
}
```
