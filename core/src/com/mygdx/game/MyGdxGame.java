package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;


import com.badlogic.gdx.scenes.scene2d.Stage;
//https://github.com/libgdx/libgdx/wiki/2D-Animation
public class MyGdxGame extends ApplicationAdapter implements ApplicationListener {
    private static final int FRAME_COLS = 5;     // #1
    private static final int FRAME_ROWS = 3;     // #2

    Animation walkAnimation;      // #3
    Texture walkSheet;      // #4
    TextureRegion[] walkFrames;     // #5
    SpriteBatch spriteBatch;        // #6
    TextureRegion currentFrame;       // #7

    float stateTime;                    // #8

    @Override
    public void create() {
        walkSheet = new Texture(Gdx.files.internal("explosion.png")); // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);              // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.05f, walkFrames);      // #11
        spriteBatch = new SpriteBatch();                // #12
        stateTime = 0f;                         // #13
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);                        // #14
        Gdx.gl.glClearColor(0.5f, 0.9f, 0.9f, 1);
        stateTime += Gdx.graphics.getDeltaTime();           // #15
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);  // #16
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 500, 500, 500, 500);             // #17
        spriteBatch.end();
    }

}