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
![untitled](https://user-images.githubusercontent.com/4397770/131383204-d620d56d-82d5-41cc-9d45-bdc25656ce59.gif)



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

## Methods

```java

    multiSpinnerSelect.getSelectedProduct()
    

```












