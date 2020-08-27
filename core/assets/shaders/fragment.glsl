#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;

uniform vec4 red;
uniform vec4 green;
uniform vec4 blue;
uniform float redLuminosity;
uniform float greenLuminosity;
uniform float blueLuminosity;
uniform float redIntensity;
uniform float greenIntensity;
uniform float blueIntensity;

void main() {
    vec4 unmodified = v_color * texture2D(u_texture, v_texCoords);
    float brightness = (unmodified.r + unmodified.g + unmodified.b) * (2.0 / 3.0) - 1.0;

    vec3 col = (unmodified.r * red.rgb * redIntensity + red.rgb * redLuminosity * brightness) +
    (unmodified.g * green.rgb * greenIntensity + green.rgb * greenLuminosity * brightness) +
    (unmodified.b * blue.rgb * blueIntensity + blue.rgb * blueLuminosity * brightness);
    gl_FragColor = vec4(col, unmodified.a);
}