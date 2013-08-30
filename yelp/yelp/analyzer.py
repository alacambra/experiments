import numpy as np
from matplotlib import pyplot as plt
import math
import thread
import threading
from threading import Lock
from matplotlib.pyplot import figure


def plotForCol(filename, col, samples):
    values = []
    f = open(filename, "r");
    for line in f:
        row = line[:-1].split("\t")
        values.append(row[col])
        
    for i in range(len(values)):
        total = 0
        
        if i + samples > len(values):
            end = len(values)-i
        else:
            end = samples
            
        for j in range(0,end):
            total += float(values[i+j])
            
        total = (total / samples) 
        values[i] = total
            
    x = np.arange(0, len(values))
    y = values
    return (x,y)

def runAll(f):
    
    for i in range(1,12):
        if i == 2: continue
        
        print i
        (x,y) = plotForCol(f, i, 2)
        
        fig = plt.figure(i)
        ax = fig.add_subplot(111)
        ax.plot(x,y)

    plt.show()
    
def plotter(filename, col, samples):    
    
    (x,y) = plotForCol(filename, col, samples)
    fig = plt.figure(col)
    ax = fig.add_subplot(111)
    ax.plot(x,y)
    
def runAllWithThreads(f):
   
    th = []
    
    for i in range(1,12):
       
        if i == 2: continue
        thread = threading.Thread(target=plotter, args=(f, i, 2))
        th.append(thread)
        thread.start()
        
    for t in th:
        t.join()

    #plt.show()

if __name__ == '__main__':
    d = "/home/albert/sql/";
    #runAll(d + "all_valorations.txt")
    runAllWithThreads(d + "all_valorations.txt")
    # reviewUsefulGraph(f)
    #plotForCol(f, 1, 1)
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    #for line in f:
    #    print(line)
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
