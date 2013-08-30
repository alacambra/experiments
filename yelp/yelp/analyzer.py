import numpy as np
from matplotlib import pyplot as plt
import thread
import threading
import multiprocessing


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

def plotter(result_queue, filename, col, samples):    
    
    (x,y) = plotForCol(filename, col, samples)
    result_queue.put((col, x, y))
    
def multiprocessingRunner(filename):
    processs = []
    result_queue = multiprocessing.Queue()
    
    for n in range(1,12): # start 4 processes crawling for the result
        if n == 2: continue
        print n
        process = multiprocessing.Process(target=plotter, args=[result_queue, filename, n, 1000])
        process.start()
        processs.append(process)
    
    result = []
    
    for i in range(1, 11):
        result.append(result_queue.get()) # waits until any of the proccess have `.put()` a result
    
    for process in processs: # then kill them all off
        process.terminate()
    
    for res in result:
        fig = plt.figure(res[0])
        ax = fig.add_subplot(111)
        ax.plot(res[1], res[2])
        
    plt.show()
    
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
    multiprocessingRunner(d + "all_valorations.txt")
    # reviewUsefulGraph(f)
    #plotForCol(f, 1, 1)
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    #for line in f:
    #    print(line)
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
