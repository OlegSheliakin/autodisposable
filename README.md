# AutoDisposable

[ ![Download](https://api.bintray.com/packages/olegsheliakin/maven/autodisposable/images/download.svg) ](https://bintray.com/olegsheliakin/maven/autodisposable/_latestVersion)

AutoDisposable is a lightweight library helping to clear Disposables automatically.

``` kotlin

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.name

    private val autoDisposable = AutoDisposable.create(this as LifecycleOwner)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe { Log.d(TAG, "Next value: $it") }
            .disposeOnPause(autoDisposable)

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe { Log.d(TAG, "Next value: $it") }
            .disposeOnStop(autoDisposable)

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe { Log.d(TAG, "Next value: $it") }
            .disposeOnDestroy(autoDisposable)
    }
```

It's greate to use along with RxBindings.

``` kotlin
 RxView.click(button)
            .subscribe { Log.d(TAG, "Next value: $it") }
            .disposeOnDestroy(autoDisposable)
```

# License
```
The MIT License (MIT)
=====================

Copyright © 2019 Oleg Sheliakin

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the “Software”), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
```
