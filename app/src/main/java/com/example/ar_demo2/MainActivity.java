package com.example.ar_demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArFragment arFragment;
    private ModelRenderable bearrenderable ,cowrenderable,dogrenderable,catrenderable,elephantrenderable,ferretrenderable;
    ImageView bear,cow,dog,cat,elephant,ferret;
    View array[];
    ViewRenderable name_animal;
    int selected=1;
    //ViewRenderable animal_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fragment
        arFragment=(ArFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);

        //animal view'ssss
        bear=(ImageView)findViewById(R.id.bear);
        cat=(ImageView) findViewById(R.id.cat);
        dog=(ImageView)findViewById(R.id.dog);
        cow=(ImageView)findViewById(R.id.cow);
        elephant=(ImageView)findViewById(R.id.elephant);
        ferret=(ImageView)findViewById(R.id.ferret);
        setanimalsArrray();
        eventonclick(); //event Handling on click
        setupModel();
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {

                    Anchor anchor=hitResult.createAnchor();
                    AnchorNode anchorNode=new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    createModel(anchorNode,selected);

            }
        });
    }

    private void setupModel()
    {  ViewRenderable.builder()
            .setView(this,R.layout.name_animal)
            .build().thenAccept(renderable ->name_animal=renderable);



        ModelRenderable.builder().setSource(this,R.raw.bear)
                .build().thenAccept(renderable ->bearrenderable=renderable)
                .exceptionally(throwable ->
                {
                    Toast.makeText(this,"Cannot Load 3D model BEAR",Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder().setSource(this,R.raw.cat)
                .build().thenAccept(renderable ->catrenderable=renderable)
                .exceptionally(throwable ->
                {
                    Toast.makeText(this,"Cannot Load 3D model CAT",Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder().setSource(this,R.raw.cow)
                .build().thenAccept(renderable ->cowrenderable=renderable)
                .exceptionally(throwable ->
                {
                    Toast.makeText(this,"Cannot Load 3D model COW ",Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder().setSource(this,R.raw.dog)
                .build().thenAccept(renderable ->dogrenderable=renderable)
                .exceptionally(throwable ->
                {
                    Toast.makeText(this,"Cannot Load 3D model DOG",Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder().setSource(this,R.raw.elephant)
                .build().thenAccept(renderable ->elephantrenderable=renderable)
                .exceptionally(throwable ->
                {
                    Toast.makeText(this,"Cannot Load 3D model ELEPHANT ",Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder().setSource(this,R.raw.ferret)
                .build().thenAccept(renderable ->ferretrenderable=renderable)
                .exceptionally(throwable ->
                {
                    Toast.makeText(this,"Cannot Load 3D model FERRET",Toast.LENGTH_SHORT).show();
                    return null;
                });
    }

    private void createModel(AnchorNode anchorNode, int selected)
    {
           if(selected==1)
           {
               TransformableNode bear=new TransformableNode(arFragment.getTransformationSystem());
               bear.getScaleController().setMaxScale(10f);
               bear.getScaleController().setMinScale(5f);
               bear.setParent(anchorNode);
               bear.setRenderable(bearrenderable);
               bear.select();

               name(anchorNode,bear,"Bear");
           }
        if(selected==2)
        {
            TransformableNode bear=new TransformableNode(arFragment.getTransformationSystem());
            bear.getScaleController().setMaxScale(10f);
            bear.getScaleController().setMinScale(5f);
            bear.setParent(anchorNode);
            bear.setRenderable(catrenderable);
            bear.select();

            name(anchorNode,bear,"Cat");
        }
        if(selected==3)
        {
            TransformableNode bear=new TransformableNode(arFragment.getTransformationSystem());
            bear.getScaleController().setMaxScale(10f);
            bear.getScaleController().setMinScale(5f);
            bear.setParent(anchorNode);
            bear.setRenderable(dogrenderable);
            bear.select();

            name(anchorNode,bear,"Dog");
        }
        if(selected==4)
        {
            TransformableNode bear=new TransformableNode(arFragment.getTransformationSystem());
            bear.getScaleController().setMaxScale(10f);
            bear.getScaleController().setMinScale(5f);
            bear.setParent(anchorNode);
            bear.setRenderable(cowrenderable);
            bear.select();

            name(anchorNode,bear,"Cow");
        }
        if(selected==5)
        {
            TransformableNode bear=new TransformableNode(arFragment.getTransformationSystem());
            bear.getScaleController().setMaxScale(10f);
            bear.getScaleController().setMinScale(5f);
            bear.setParent(anchorNode);
            bear.setRenderable(elephantrenderable);
            bear.select();

            name(anchorNode,bear,"Elephant");
        }
        if(selected==6)
        {
            TransformableNode bear=new TransformableNode(arFragment.getTransformationSystem());
            bear.getScaleController().setMaxScale(10f);
            bear.getScaleController().setMinScale(5f);
            bear.setParent(anchorNode);
            bear.setRenderable(ferretrenderable);
            bear.select();

            name(anchorNode,bear,"Ferret");
        }
    }

    private void name(AnchorNode anchorNode, TransformableNode model, String name)
    {
        TransformableNode name_view=new TransformableNode(arFragment.getTransformationSystem());
        name_view.setLocalPosition(new Vector3(0f,model.getLocalPosition().y+0.25f,0));
        name_view.setParent(anchorNode);
        name_view.setRenderable(name_animal);
        name_view.select();


        TextView text_name=(TextView)name_animal.getView();
        text_name.setText(name);
        text_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anchorNode.setParent(null);
            }
        });
    }

    private void eventonclick()
    {
        for(int i=0;i<array.length;i++)
        {
            array[i].setOnClickListener(this);
        }
    }


    private void setanimalsArrray()
    {
        array=new View[]{bear,cat,dog,cow,elephant,ferret};
    }

    @Override
    public void onClick(View v)
    {
          if(v.getId()==R.id.bear) {
              selected = 1;
              setBackground(v.getId());
          }
          else if(v.getId()==R.id.cat)
          {selected=2;
              setBackground(v.getId());
          }
          else if(v.getId()==R.id.dog) {
              selected = 3;
              setBackground(v.getId());
          }
          else if(v.getId()==R.id.cow) {
              selected = 4;
              setBackground(v.getId());
          }
          else if(v.getId()==R.id.elephant) {
              selected = 5;
              setBackground(v.getId());
          }
          else if(v.getId()==R.id.ferret)
          { selected=6;
              setBackground(v.getId());
          }

    }

    private void setBackground(int id)
    {
        for(int i=0;i<array.length;i++)
        {
            if(array[i].getId()==id)
            {
                array[i].setBackgroundColor(Color.parseColor("#80333639"));
            }
            else
            {
                array[i].setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }
}