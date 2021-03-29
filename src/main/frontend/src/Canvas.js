import React, { useRef, useEffect } from 'react';

function App() {
    const canvas = useRef();
    let ctx = null;

    // initialize the canvas context
    useEffect(() => {
        // dynamically assign the width and height to canvas
        const canvasEle = canvas.current;
        canvasEle.width = canvasEle.clientWidth;
        canvasEle.height = canvasEle.clientHeight;

        // get context of the canvas
        ctx = canvasEle.getContext("2d");
    }, []);

    useEffect(() => {
        drawLine({ x: 20, y: 20, x1: 20, y1: 100 });

        drawLine({ x: 50, y: 50, x1: 200, y1: 100 }, { color: 'red' });

        drawLine({ x: 300, y: 250, x1: 260, y1: 70 }, { color: 'green', width: 5 });

        drawLine({ x: 70, y: 240, x1: 160, y1: 120 }, { color: 'blue' });
    }, []);

    // draw a line
    const drawLine = (info, style = {}) => {
        const { x, y, x1, y1 } = info;
        const { color = 'black', width = 1 } = style;

        ctx.beginPath();
        ctx.moveTo(x, y);
        ctx.lineTo(x1, y1);
        ctx.strokeStyle = color;
        ctx.lineWidth = width;
        ctx.stroke();
    }

    return (
        <div className="App">
            <h3>Draw a line on Canvas - <a href="http://www.cluemediator.com" target="_blank" rel="noopener noreferrer">Clue Mediator</a></h3>
            <canvas ref={canvas}></canvas>
        </div>
    );
}

export default App;