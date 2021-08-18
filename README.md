# Spinner Multi choice




## MultiSpinnerSelect with ArrayList&lt;collection>



## How to implement
[![](https://jitpack.io/v/grimarj89/MultiSpinnerSelect.svg)](https://jitpack.io/#grimarj89/MultiSpinnerSelect)



Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy


	allprojects {
		repositories {
			//...
			maven { url 'https://jitpack.io' }
		}
	}


```

Step 2. Add the dependency

```groovy
    dependencies {
	        implementation 'com.github.grimarj89:MultiSpinnerSelect:$version'

	}

```






## Capture

![alt tag](https://cloud.githubusercontent.com/assets/4397770/9688649/32270ea4-52f6-11e5-872f-8e4e87fb6052.png)

![alt tag](https://cloud.githubusercontent.com/assets/4397770/9688322/139dae04-52f4-11e5-8cac-f6981114e232.png)

![alt tag](https://cloud.githubusercontent.com/assets/4397770/9688323/13a7b1e2-52f4-11e5-863e-e261dc2b3b26.png)

![alt tag](https://cloud.githubusercontent.com/assets/4397770/9688321/1382d9f8-52f4-11e5-976c-407fafdbe622.png)



## How to use


```xml

<com.grimgdl.ui.widget.MultiSpinnerSelect
    android:id="@+id/spinner"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
    


```


```java

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        multiSpinnerSelect = findViewById(R.id.spinner_product);
        multiSpinnerSelect.setItems(getProducts(),"Nothing Selected",this);
}

private ArrayList<Product> getProducts(){
        ArrayList<Product> arrayList = new ArrayList<>();
        for (int i=0; i < 20; i++){
        final Product producto = new Product(i + 1, "Producto " + i, "gr2" + i);


        arrayList.add(producto);
        }


        return arrayList;
}



```












