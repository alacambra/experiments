import numpy as np
from matplotlib import pyplot as plt
import multiprocessing
from functools import total_ordering

def getToalCols(filename):
    f = open(filename, "r");
    for line in f:
        row = line[:-1].split("\t")
        return len(row)

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
    res = (filename + "(" + str(col) + ")", x,y)
    result_queue.put(res)
    
    #used for monorunner
    return res
    
def multiprocessingRunner(filesname, samples):
    processs = []
    result_queue = multiprocessing.Queue()
    allCols = 0;
    allIgnores = 0;
    for filename in filesname:
        ignore = filename[1]
        allIgnores = allIgnores + len(ignore)        
        totalCols = getToalCols(filename[0])
        allCols = allCols + totalCols
         
        for n in range(0,totalCols): # start 4 processes crawling for the result
           
            if n in ignore: continue
            print n
            process = multiprocessing.Process(target=plotter, args=[result_queue, filename[0], n, samples])
            process.start()
            processs.append(process)
    
    result = []
    
    for i in range(0, allCols-allIgnores):
        result.append(result_queue.get()) # waits until any of the proccess have `.put()` a result
        print str(i) + " ready"
    
    for process in processs: # then kill them all off
        process.terminate()
    
    for res in result:
        fig = plt.figure(res[0])
        ax = fig.add_subplot(111)
        ax.plot(res[1], res[2])
        
    plt.show()
    
def monoRunner(filename, samples, ignore = []):
    result_queue = multiprocessing.Queue()
    totalCols = getToalCols(filename)
    result = []
    for n in range(0,totalCols): # start 4 processes crawling for the result
       
        if n in ignore: continue
        print n
        res = plotter(result_queue, filename, n, samples)
        result.append(res)
    
    for res in result:
        fig = plt.figure(res[0])
        ax = fig.add_subplot(111)
        ax.plot(res[1], res[2])
        
    plt.show()
    
if __name__ == '__main__':
    d = "/home/albert/sql/";
    #multiprocessingRunner(d + "all_valorations.txt", 10, [0,2])
    #multiprocessingRunner([d + "all_valorations_bussines.txt"], 500, [])
    multiprocessingRunner([(d + "all_valorations_bussines.txt", []), (d + "all_valorations_bussines_open.txt", [])], 50)
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    #for line in f:
    #    print(line)
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
